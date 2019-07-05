package kensyuJuly3rd.problem3_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import kensyuJuly3rd.problem3.DBRelation;
/**
 * DBの接続・切断処理と複数のSQL文を扱う処理のクラス
 * @author k_oda
 *
 */
public class DBRelation2 extends DBRelation{

	/**
	 * DBに接続する処理
	 * @throws SQLException
	 */
	public static void getConnection() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);//SQLへの接続
		conn.setAutoCommit(false);//commit:上書き不能にする機能
	}

	/**
	 * csvファイルからDBにおみくじの情報を登録、更新する処理
	 *
	 * @param userName	作成者名or更新者名
	 * @param today		作成日or更新日
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void setOmikujTable(String userName, Date today) throws SQLException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("fortune.csv")));
		String line;
		int i = 0;
		String[] fortuneList = new String[4];
		int fortuneId = 0;

		/**
		 * 配列におみくじ1枚分の情報を、リストにおみくじそのものを格納
		 */
		while((line = br.readLine()) != null) {
			if (i != 0) {//1行目は各項目名になっているため省く

				//おみくじ1枚分の情報を配列に格納
				fortuneList = line.split(",");

				/**
				 * 運勢名による運勢コードの決定
				 */
				switch(fortuneList[0]) {
					case "大吉":
						fortuneId = 1;
						break;

					case "中吉":
						fortuneId = 2;
						break;

					case "小吉":
						fortuneId = 3;
						break;

					case "末吉":
						fortuneId = 4;
						break;

					case "吉":
						fortuneId = 5;
						break;

					case "凶":
						fortuneId = 6;
						break;


				}

				/**
				 * おみくじテーブルの登録・更新のブロック
				 */
				if(DBSelect2.getOmikujiTable(i)) {
					DBInsert2.insertOmikujiTable(i, fortuneId, fortuneList[1], fortuneList[2], fortuneList[3], userName, today);
				} else {
					String[] updater = DBSelect2.checkOmikujiTable(i);
					if(updater != null) {
						int fortuneCode = Integer.parseInt(updater[0]);

						if(fortuneId != fortuneCode) {
							DBUpdate2.updateFortuneId(fortuneId, userName, today, i);
						}

						if(!fortuneList[1].equals(updater[1])) {
							DBUpdate2.updateNegaigoto(fortuneList[1], userName, today, i);
						}

						if(!fortuneList[2].equals(updater[2])) {
							DBUpdate2.updateAkinai(fortuneList[2], userName, today, i);
						}

						if(!fortuneList[3].equals(updater[3])) {
							DBUpdate2.updateGakumon(fortuneList[3], userName, today, i);
						}
					}
				}
			}
			/**
			 * CSVを次の行へ移動
			 */
			i++;
		}
		/**
		 * 登録・更新データの保存とcsvファイル読み込みの終了
		 */
		conn.commit();
		br.close();
	}

	/**
	 * DBとの接続をきるメソッド
	 * @throws SQLException
	 */
	public static void closeConnection(){
		try {
		if(rset != null)rset.close();
		if(ps != null)ps.close();
		if(conn != null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
