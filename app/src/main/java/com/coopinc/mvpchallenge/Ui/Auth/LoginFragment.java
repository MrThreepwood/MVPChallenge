package com.coopinc.mvpchallenge.ui.auth;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.coopinc.mvpchallenge.R;
import com.coopinc.mvpchallenge.ui.IBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment implements ILoginView {
    @Bind(R.id.name)
    EditText etName;
    @Bind(R.id.email)
    EditText etEmail;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private ILoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, (IBaseActivity) getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setNameError(String error) {
        etName.setError(error);
    }

    @Override
    public void setEmailError(String error) {
        etEmail.setError(error);
    }

    @Override
    public void showOtherError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.submit)
    public final void onSubmit() {
        presenter.logIn(etName.getText().toString(), etEmail.getText().toString());
    }
}
