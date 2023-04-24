package com.example;

import com.example.model.LoginUser;
import com.example.model.MessageDTO;
import com.example.model.Messages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.NoArgsConstructor;

/**
 * Jakarta MVCのコンロトーラクラスです。@Controllerアノテーションを付けましょう。
 * 
 * コントローラクラスはCDI管理Beanであることが必須で、必ず@RequestScopedを付けます。
 * 
 * CDI管理Beanには引数のないコンストラクタが必須なので、
 * Lombokの@NoArgsConstructorで空っぽのコンストラクタを作成します。
 * ただし、このクラスは宣言時に初期化してないfinalフィールドを持つため、
 * このままだとフィールドが初期化されない可能性があってコンパイルエラーとなります。
 * よって、force=true指定で該当フィールドを0かfalseかnullで初期化する処理を追加します。
 * 
 * このクラスはJAX-RSのリソースクラスに似ています。
 * @Path はこのクラス全体が扱うURLのパスで、JAX-RSのアノテーションです。
 * これは @ApplicationPath からの相対パスとなります。
 * パスの先頭の/と末尾の/はあってもなくても同じです。
 */
@Controller
@RequestScoped
@NoArgsConstructor(force = true)
@Path("/")
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
	 * @GET や @POST アノテーションは、メソッドが処理するHTTPリクエストメソッドを特定する
	 * リクエストメソッド指示子（Request method designator：JAX-RSのアノテーションです）
	 * 
	 * @Path がないため、このメソッドはクラス全体が扱うURLのパスを扱います。
	 */
	@GET
	public String home() {
		// ViewのJSPファイル名を返します。
		// JSPファイルはデフォルトでは /webapp/WEB-INF/views の下に置きます。
		return "index.jsp";
	}
	
	/**
	 * @Path はこのクラス全体が扱うURLのパスからの相対パスです。
	 */
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
