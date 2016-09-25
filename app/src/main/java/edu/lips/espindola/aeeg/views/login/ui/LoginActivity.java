package edu.lips.espindola.aeeg.views.login.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.lips.espindola.aeeg.R;
import edu.lips.espindola.aeeg.domain.ConnectionTask;
import edu.lips.espindola.aeeg.views.login.presenter.LoginPresenter;
import edu.lips.espindola.aeeg.views.login.presenter.LoginPresenterImpl;
import edu.lips.espindola.aeeg.views.login.repository.LoginRepository;
import edu.lips.espindola.aeeg.views.login.repository.LoginRepositoryImpl;

public class LoginActivity extends AppCompatActivity implements LoginView {


    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.inputsWrapper)
    LinearLayout inputsWrapper;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle(R.string.login_title);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImpl(this);
        presenter.onCreate();
        presenter.checkForAuthenticatedUser();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        presenter.validateLogin(inputEmail.getText().toString(),
                inputPassword.getText().toString());
    }

    @Override
    public void handleSignUp() {
       // presenter.registerNewUser();
    }

    @Override
    public void navigateToMainScreen() {

    }

    @Override
    public void navigateToSignupScreen() {

    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void newUserSuccess() {

    }

    @Override
    public void newUserError(String error) {

    }

    @Override
    public void showSnackBarMessage(String response) {
        Snackbar.make(inputsWrapper, response, Snackbar.LENGTH_INDEFINITE).show();
    }

    void setInputs(boolean enabled){ // controles de entrada
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
    }
}
