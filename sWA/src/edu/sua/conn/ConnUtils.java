package edu.sua.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 
public class ConnUtils {

	private static DataSource ds;

	private static boolean initialised;

	static {
		initialised = false;
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sWA");
			if (ds == null)
				throw new SQLException("Unknown DataSource 'jdbc/sWA'");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Connect to any DB configured in META-INF/context.xml
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (!initialised) {
			h2dbInit();
			initialised = true;
		}
		return ds.getConnection();
	}

	/*
	 * инициализация встроенной Н2 базы данных, при расположении её в памяти (а при
	 * старте такой СУБД память пуста, таблиц и данных нет). возможна параметром
	 * ;INIT='~/script.sql', в котором указывается (полный)путь к скрипту с командами SQL.
	 * url="jdbc:h2:mem:sWA;DB_CLOSE_DELAY=-1;INIT=runscript from 'SQL.txt'" (это из
	 * context.xml). однако при использовании в публикуемом приложении, и
	 * использовании относительного пути, драйвер базы не находит скрипт нигде в
	 * каталогах приложения, в том числе в classpath: и WEB-INF/lib. можно
	 * использовать System.setProperty(SysProperties.H2_SCRIPT_DIRECTORY, ... для
	 * указания где искать скрипты. но тогда нужно вмешательство в конфиги Tomcat.
	 * для сохранения независимости приложения от изменения настроек сервера,
	 * инициализация базы данными сделана через этот метод.
	 */

	private static void h2dbInit() throws SQLException {
//		System.out.println(">>> h2dbInit();");
		List<String> ddl = dbPopulate.getDdl();
		List<String> dml = dbPopulate.getDml();
		Connection c = ds.getConnection();
		Statement s = c.createStatement();		
		for (String sql : ddl) {
			s.executeUpdate(sql);
		}
		for (String sql : dml) {
			s.executeUpdate(sql);
		}
		s.close();
		c.close(); // ?
	}

}