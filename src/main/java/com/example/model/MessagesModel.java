package com.example.model;

import java.util.ArrayList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * MessagesModelはArrayListを継承するリストクラスです。
 */
/**
 * @ApplicationScoped アノテーションを付けることで、CDI beanとして宣言しています。
 * このクラスのインスタンスのライフサイクルは、
 * アプリケーションスコープ（アプリが起動してから終了するまで）となります。
 */
/**
 * CDI beanに名前を付ける場合、@Namedアノテーションを用います。
 * 例えば、@Named("FOO")と付けると"FOO"という名前になりますが、
 * 引数を省略すると、クラス名の先頭を小文字にした名前、
 * 下記では "messagesModel" という名前になります。
 * 例えば、JSP内ではこの名前でインスタンスを参照することができます。
 */
@ApplicationScoped
@Named
public class MessagesModel extends ArrayList<MessageDTO> {
}
