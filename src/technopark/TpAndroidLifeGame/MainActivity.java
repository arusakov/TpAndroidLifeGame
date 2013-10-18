package technopark.TpAndroidLifeGame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button btnStart, btnSettings, btnClear;
	private MainSettingsDialog settingsDialog;
	private boolean gameStarted = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		settingsDialog = new MainSettingsDialog();

		btnStart = (Button) findViewById(R.id.mainBtnStart);
		btnSettings = (Button) findViewById(R.id.mainBtnSettings);
		btnClear = (Button) findViewById(R.id.mainBtnClear);

		btnStart.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.mainBtnStart:
				btnStartClick();
				break;
			case R.id.mainBtnSettings:
				showSettingsDialog();
				break;
		}

	}

	private void btnStartClick() {
		if (gameStarted) {
			btnStart.setText(R.string.START);
			btnClear.setEnabled(true);
		}
		else {
			btnStart.setText(R.string.STOP);
			btnClear.setEnabled(false);
		}
		gameStarted = !gameStarted;
	}

	private void showSettingsDialog() {
		Dialog dialog = new Dialog(this);

		dialog.setTitle(getResources().getString(R.string.SETTINGS));
		dialog.setContentView(R.layout.settings);

		settingsDialog.setDialog(dialog);

		dialog.findViewById(R.id.settingsBtnOk).setOnClickListener(settingsDialog);
		dialog.findViewById(R.id.settingsBtnCancel).setOnClickListener(settingsDialog);

		dialog.show();
	}
}
