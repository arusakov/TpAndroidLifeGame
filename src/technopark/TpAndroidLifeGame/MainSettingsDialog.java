package technopark.TpAndroidLifeGame;


import android.app.Dialog;
import android.view.View;

public class MainSettingsDialog implements View.OnClickListener {

	private Dialog dialog;

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.settingsBtnOk) {

		}
		dialog.dismiss();
	}
}
