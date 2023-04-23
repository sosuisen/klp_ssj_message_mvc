package com.example;

import com.example.model.LoginUser;
import com.example.model.MessageDTO;
import com.example.model.Messages;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.NoArgsConstructor;

/**
 * Jakarta MVCのコンロトーラークラスです。@Controllerアノテーションを付けましょう。
 * 
 * CDIのデフォルト設定では、
 * コントロールクラスにはスコープアノテーションが必須です。
 * （つまりCDI管理Beanにする必要があります。下記では@ApplicationScoped）
 * 
 * JAX-RSではリソースクラスに相当します。
 * @Path はこのクラス全体が扱うURLのパスで、
 * @ApplicationPath からの相対パスとなります。
 * @Path はJAX-RSのアノテーションです。
 * 
 * CDI管理Beanには引数のないコンストラクタが必須なので、
 * Lombokの@NoArgsConstructorで空っぽのコンストラクタを作成します。
 * ただし、このクラスは初期化してないfinalフィールドを持つため、
 * このままだとフィールドが初期化されない可能性のためコンパイルエラーとなります。
 * force=true指定で強制的に該当フィールドを0かfalseかnullで初期化する処理を追加します。
 * （ここまでやらせておいて、実際には@Inject付きのコンストラクタが呼ばれるため、
 *  こちらは呼ばれないコンストラクタです。）
 */
@ApplicationScoped
@Controller
@Path("")
@NoArgsConstructor(force = true)
public class MyController {
	private final Messages messages;

	private final LoginUser loginUser;

	// コンストラクタインジェクションを用います。
	@Inject
	public MyController(Messages messages, LoginUser loginUser) {
		this.messages = messages;
		this.loginUser = loginUser;
	}

	/**
	 * @GET や @POST アノテーションは、メソッドが処理するHTTPリクエストメソッドを特定します。
	 * （JAX-RSのアノテーションです）
	 * @Path はクラス全体の @Path からの相対パスです。
	 */
	@GET
	@Path("")
	public String home() {
		// ViewのJSPファイル名を返します。
		// JSPファイルはデフォルトでは /webapp/WEB-INF/views の下に置きます。
		return "index.jsp";
	}

	@GET
	@Path("list")
	public String getMessage() {
		// 今回はここで強制的にユーザ名をセットしておきます。
		// 今後は、ログイン処理を追加し、その時にセットする必要があります。
		this.loginUser.setName("鴨川三条");

		return "list.jsp";
	}

	@POST
	@Path("list")
	public String postMessage(@BeanParam MessageDTO mes) {
		messages.add(mes);
		return "list.jsp";
	}

	@GET
	@Path("clear")
	public String clearMessage() {
		messages.clear();
		// リダイレクトは "redirect:リダイレクト先のパス"
		return "redirect:list";
	}

}
