package com.example.model;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * LoginUserはログインユーザの情報を保持するクラスです。
 */
/**
 * @SessionScoped アノテーションを付けることで、CDI管理Beanとして宣言しています。
 * このクラスのインスタンスのライフサイクルは、
 * セッションスコープ（セッションが有効な間）となります。
 */
/**
 * CDI管理Beanに名前を付ける場合、@Namedアノテーションを用います。
 * 例えば、@Named("FOO")と付けると"FOO"という名前になりますが、
 * 引数を省略すると、クラス名の先頭を小文字にした名前、
 * 下記では "loginUser" という名前になります。
 * 例えば、JSP内ではこの名前でインスタンスを参照することができます。
 */
/**
 * @Setter, @Getter はLombokのアノテーションです。
 */
@SessionScoped
@Named
@Setter @Getter
public class LoginUser implements Serializable {
	private String name = null;
}

