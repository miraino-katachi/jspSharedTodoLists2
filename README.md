# JSP/Servletで作る共有TODOリスト
JSP/Servletで「共有TODOリスト」を作ってみました。
書籍「スッキリわかるサーブレット＆JSP入門」を一通り学習して作成しています。
書籍に載っていないところは、ネットで調べて実装しています。

## PHPの共有TODOリストと異なるところ
- テーブル構造が一部異なります。sql/ddl.sqlをご参照ください。

## 工夫したところ
1. Controllerをスリムにしたい
   - Controllerの肥大化を避けるために、できるだけロジックを別のクラスに書こうと努力しています。
   - Controllerの肥大化を避けるために、Filterクラスを多用しています。
     - どのFilterをどのServletに適用するかについては、「web.xml」に記述しています。
2. コーディングを楽にしたい
   - バリデーションの処理に「commons-validator-1.7」を利用しています。
3. その他
   - 今後も使いまわしできそうなロジックは、「com.katachi.miraino」というパッケージを作って、そこに入れています。

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
