package br.com.wmitrut.cadastrousuarios;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "usuarios.db";
	private static final int DATABASE_VERSION = 3;
	private static DatabaseHelper databaseHelper;

	public DatabaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static DatabaseHelper getInstance(Context context) {
		if (databaseHelper == null) {
			databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		return databaseHelper;
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connection) {
		try {
			TableUtils.createTableIfNotExists(connection, Usuario.class);
			TableUtils.createTableIfNotExists(connection, Pessoa.class);
			TableUtils.createTableIfNotExists(connection, Compromissos.class);
		} catch (SQLException e) {
		}
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final ConnectionSource connectionSource, int oldVersion, final int newVersion) {
		if (oldVersion == 1) {
			try {
				TableUtils.createTableIfNotExists(connectionSource, Pessoa.class);
			} catch (SQLException e) {
			}
		}
		if (oldVersion <= 2) {
			try {
				TableUtils.createTableIfNotExists(connectionSource, Compromissos.class);
			} catch (SQLException e) {
			}
		}
		if (oldVersion <= 3) {
		}
	}
}
