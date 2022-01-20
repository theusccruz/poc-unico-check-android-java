package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.iAcessoBioSelfie;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCamera;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener;
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener;
import com.acesso.acessobio_android.services.dto.ErrorBio;
import com.acesso.acessobio_android.services.dto.ResultCamera;

public class MainActivity extends AppCompatActivity {


//    UnicoCheckCamera unicoCheckCamera = acessoBioBuilder
//            .setAutoCapture(false)
//            .setSmartFrame(false)
//            .build();


//    public void openCamera() {
//        iAcessoBioSelfie cameraListener = new iAcessoBioSelfie() {
//            @Override
//            public void onSuccessSelfie(ResultCamera result) { }
//
//            @Override
//            public void onErrorSelfie(ErrorBio errorBio) { }
//        };
//
//        unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
//            @Override
//            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
//                cameraOpener.open(cameraListener);
//            }
//
//            @Override
//            public void onCameraFailed(String message) { }
//        });
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, new AcessoBioListener() {
        @Override
        public void onErrorAcessoBio(ErrorBio errorBio) { }

        @Override
        public void onUserClosedCameraManually() { }

        @Override
        public void onSystemClosedCameraTimeoutSession() { }

        @Override
        public void onSystemChangedTypeCameraTimeoutFaceInference() { }
    });
}