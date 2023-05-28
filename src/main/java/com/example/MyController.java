package com.example;

import com.example.model.LoginUserModel;
import com.example.model.MessageDTO;
import com.example.model.MessagesModel;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.NoArgsConstructor;

/**
 * Jakarta MVCのコンロトーラクラスです。@Controllerアノテーションを付けましょう。
 * 
 * コントローラクラスはCDI beanであることが必須で、必ず@RequestScopedを付けます。
 * 
 * CDI beanには引数のないコンストラクタが必須なので、
 * Lombokの@NoArgsConstructorで空っぽのコンストラクタを作成します。
 * ただし、このクラスは宣言時に初期化してないfinalフィールドを持つため、
 * このままだとフィールドが初期化されない可能性があってコンパイルエラーとなります。
 * よって、force=true指定で該当フィールドを0かfalseかnullで初期化する処理を追加します。
 */
@Controller
@RequestScoped
@NoArgsConstructor(force = true)
@Path("/")
public class MyController {
	private final Models models;
	
	private final MessagesModel messagesModel;

	private final LoginUserModel loginUserModel;

	// CDI Beanの@Injectはコンストラクタインジェクションを用いるのが定石です。
	@Inject
	public MyController(Models models, MessagesModel messagesModel, LoginUserModel loginUserModel) {
		this.models = models;
		this.messagesModel = messagesModel;
		this.loginUserModel = loginUserModel;
	}

	/**
	 * @Path がないため、このメソッドはクラス全体が扱うURLのパスを扱います。
	 */
	@GET
	public String home() {
		models.put("appName", "メッセージアプリ");
		return "index.jsp";
	}

	@GET
	@Path("list")
	public String getMessage() {
		// 今回はここで強制的にユーザ名をセットしておきます。
		// 今後は、ログイン処理を追加し、その時にセットする必要があります。
		this.loginUserModel.setName("鴨川三条");

		return "list.jsp";
	}

	@POST
	@Path("list")
	public String postMessage(@BeanParam MessageDTO mes) {
		messagesModel.add(mes);
		// リダイレクトは "redirect:リダイレクト先のパス"
		return "redirect:list";
	}

	@GET
	@Path("clear")
	public String clearMessage() {
		messagesModel.clear();
		return "redirect:list";
	}
}
