package com.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * このアプリを定義するクラスです。
 * JAX-RSのクラス Application を継承して、
 * @ApplicationPath アノテーションを付ける必要があります。
 * 
 * @ApplicationPath は、このアプリが呼ばれるURLのパスで、
 * コンテキストルート（通常はプロジェクト名）からの相対パスを書きます。
 * "/msg"を指定した場合のURLの例） http://localhost:8080/プロジェクト名/msg/
 */
@ApplicationPath("/msg")
public class MyApplication extends Application {
}
