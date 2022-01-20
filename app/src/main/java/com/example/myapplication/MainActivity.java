package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.iAcessoBioSelfie;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCamera;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener;
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener;
import com.acesso.acessobio_android.services.dto.ErrorBio;
import com.acesso.acessobio_android.services.dto.ResultCamera;

public class MainActivity extends AppCompatActivity implements AcessoBioListener {
    private IAcessoBioBuilder acessoBioBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.acessoBioBuilder = new AcessoBio(this, this);
    }

    public void openCamera(View view) {
        UnicoCheckCamera unicoCheckCamera = this.acessoBioBuilder
                .setAutoCapture(true)
                .setSmartFrame(true)
                .build();

        iAcessoBioSelfie cameraListener = new iAcessoBioSelfie() {
            @Override
            public void onSuccessSelfie(ResultCamera result) {
                System.out.println(result.getBase64());
            }

            @Override
            public void onErrorSelfie(ErrorBio errorBio) {
                System.out.println(errorBio);
            }
        };

        unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                cameraOpener.open(cameraListener);
            }

            @Override
            public void onCameraFailed(String message) {
                System.out.println(message);
            }
        });
    }

    @Override
    public void onErrorAcessoBio(ErrorBio errorBio) {
        System.out.println(errorBio.getDescription());
    }

    @Override
    public void onUserClosedCameraManually() {}

    @Override
    public void onSystemClosedCameraTimeoutSession() {}

    @Override
    public void onSystemChangedTypeCameraTimeoutFaceInference() {}
}