package kensyuJuly3rd.problem3;

/**
 * おみくじの情報の元になる抽象クラス
 * @author k_oda
 *
 */

public abstract class Omikuji implements Fortune {

	/**
	 * 上から運勢、願い事、商い、学問のフィールド
	 * 運勢のsetメソッドのみ抽象メソッドとし、その設定を個別クラスに委ねる
	 */
	protected String unsei;
	protected String negaigoto;
	protected String akinai;
	protected String gakumon;

	public abstract void setUnsei();
	public void setNegaigoto(String str){
		negaigoto = str;
	}
	public void setAkinai(String str){
		akinai = str;
	}
	public void setGakumon(String str){
		gakumon = str;
	}

	public String getUnsei() {
		return unsei;
	}

	public String getNegaigoto() {
		return negaigoto;
	}
	public String getAkinai(){
		return akinai;
	}
	public String getGakumon(){
		return gakumon;
	}

	/**
	 * おみくじ結果を表示するメソッド
	 */
	public String disp() {
		String unseiTemp = String.format(DISP_STR,unsei);
		StringBuilder sb = new StringBuilder();

		sb.append(unseiTemp);
		sb.append("\n");
		sb.append(Template.NEGAIGOTO);
		sb.append(negaigoto);
		sb.append("\n");
		sb.append(Template.AKINAI);
		sb.append(akinai);
		sb.append("\n");
		sb.append(Template.GAKUMON);
		sb.append(gakumon);
		return sb.toString();
	}

}
