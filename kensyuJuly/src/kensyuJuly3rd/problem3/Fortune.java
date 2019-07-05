package kensyuJuly3rd.problem3;

import java.util.ResourceBundle;
/**
 * 占いインターフェース
 * プロパティファイルから情報を取得
 * @author k_oda
 *
 */

public interface Fortune {
	ResourceBundle rb = ResourceBundle.getBundle("fortune", new ResourceBundleWithEncording());
	String DISP_STR = rb.getString("disp_str");
	String disp();
}
