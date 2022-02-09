package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.iAcessoBioSelfie;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.onboarding.IAcessoBioTheme;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCamera;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener;
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener;
import com.acesso.acessobio_android.services.dto.ErrorBio;
import com.acesso.acessobio_android.services.dto.ResultCamera;

public class MainActivity extends AppCompatActivity implements AcessoBioListener {
    private IAcessoBioBuilder acessoBioBuilder;
    private UnicoCheckCameraOpener.Selfie selfie;
    private iAcessoBioSelfie cameraListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.acessoBioBuilder = new AcessoBio(this, this);

        prepare();
    }

    public void prepare() {
        UnicoCheckCamera unicoCheckCamera = this.acessoBioBuilder
                .setAutoCapture(false)
                .setSmartFrame(false)
                .build();

        cameraListener = new iAcessoBioSelfie() {
            @Override
            public void onSuccessSelfie(ResultCamera result) {
                Log.d("Response", result.getEncrypted()); // getEncrypted() retorna o JWT
                Log.d("Response", result.getBase64()); // getBase64() retorna o base64
            }

            @Override
            public void onErrorSelfie(ErrorBio errorBio) { }
        };

        unicoCheckCamera.prepareSelfieCamera("unico-check-mobile-services.json", new SelfieCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                selfie = cameraOpener;
            }

            @Override
            public void onCameraFailed(String message) {
                System.out.println(message);
            }
        });
    }

    public void openCamera(View view){
        selfie.open(cameraListener);
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