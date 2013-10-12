package technopark.TpAndroidLifeGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button btnStart, btnSettings, btnClear;
	private boolean gameStarted = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnStart = (Button) findViewById(R.id.mainBtnStart);
		btnSettings = (Button) findViewById(R.id.mainBtnSettings);
		btnClear = (Button) findViewById(R.id.mainBtnClear);

		btnStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.mainBtnStart:
				btnStartClick();
				break;
		}

	}

	private void btnStartClick() {
		if (gameStarted) {
			btnStart.setText(R.string.game_start);
			btnClear.setEnabled(true);
		}
		else {
			btnStart.setText(R.string.game_stop);
			btnClear.setEnabled(false);
		}
		gameStarted = !gameStarted;
	}
}
