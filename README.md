# JSP/Servletで作る共有TODOリスト Ver.2
JSP/Servletで「共有TODOリスト」を作ってみました。

書籍「スッキリわかるサーブレット＆JSP入門」を一通り学習して作成しています。
書籍に載っていないところは、ネットで調べて実装しています。
使用に関しては、ほぼ、PHPの共有TODOリストに準拠しています（一部異なるところがあります）。

https://github.com/miraino-katachi/shared_todo_lists

実際にソースコードをダウンロードして実行してみてください。

実際に動作させるには、ソースコードをダウンロードし、ローカルに新規作成した動的Webプロジェクト（Java 11、Tomcat 9、動的Webモジュール 4.0）にダウンロードしたソースをインポートするのが確実です。

## 開発環境
- macOS 12.1（Windowsでも動作します）
- Eclipse 2021-12
- Java 11 / Tomcat 9
- MySQL 5.7.34（MariaDBでも動作します）

## PHPの共有TODOリストと異なるところ
- テーブル構造が一部異なります。sql/ddl.sqlを実行してテーブルを作成してください。
- 画面遷移が一部異なります。

## 工夫したところ
1. Servletをスリムにしたい
   - Servletの肥大化を避けるために、できるだけロジックを別のクラスに書こうと努力しています。
   - Servletの肥大化を避けるために、Filterクラスを多用しています。
     - どのFilterをどのServletに適用するかについては、「web.xml」に記述しています。
2. コーディングを楽にしたい
   - バリデーションの処理に「commons-validator-1.7」を利用しています。
3. トランザクションの制御をできるようにした
   - 書籍「スッキリわかるサーブレット＆JSP」のDAOの作り方では、SQLが実行されるたびにデータベースへの接続インスタンスを生成していたので、トランザクションの制御ができません。複数のCUD処理が行われる場合にも同じ接続インスタンスを使うように改良し、トランザクションの処理を行えるようにしました。
4. データベース接続を閉じる方法
   - try-with-resources文を使って、データベース接続を自動的に閉じることができるようにしています。
5. その他
   - 今後も使いまわしできそうなロジックは、「com.katachi.miraino」というパッケージを作って、そこに入れています。
   - バリデーションの処理を行うための基底クラスを抽象クラスにしました。

## 今後実装したい機能
1. パスワードのハッシュ化
   - ログインパスワードがテーブルに平文で保存されているので、暗号化クラスを使ってハッシュ化した後に保存するようにしたい。
2. TODO一覧にページャー（ペジネーション）をつけたい。

## その他
### GETパラメータの文字化け対策
GETパラメータで受け取った値が文字化けすることがあります。server.xmlの「Connector」タグに「useBodyEncodingForURI="true"」を追記することで解決します。
```
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
    maxThreads="150" SSLEnabled="true">
```
↓
```
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
    maxThreads="150" SSLEnabled="true" useBodyEncodingForURI="true">
```


以上です。ご参考になれば幸いです。
