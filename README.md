<h1><b>Twitter REST API DESIGN with Spring Boot Security</b></h1>
<h2><b>Twitter API</b></h2>

<h3><b>Hedef</b></h3>
<p>
Bu projenin amacı Spring Boot ile ilgili öğrendiğimiz tüm konuları pratik etmek amacıyla bir Backend projesi tasarlamaktır.
Amacımız Twitter uygulamasını biz yazsaydık nasıl yazardık, nelere dikkat ederdik, design ve implementation kısımlarını nasıl yapardık bunu test etmektir.
</p>

<h3><b>Fonksiyonel Zorunluluklar</b></h3>
<ul>
  <li>Proje Spring Boot teknolojisi kullanarak dizayn edilecektir.</li>
  <li>Veritabanı olarak PostgreSQL kullanılacaktır.</li>
</ul>

<h3><b>Endpoints</b></h3>

<h4><b>EASY</b></h4>
<ul>
  <li>POST <a href="http://localhost:3000/tweet" target="_blank">http://localhost:3000/tweet</a> → Tweet oluşturma ve veritabanına kaydetme. Tweet'in hangi kullanıcıya ait olduğu mutlaka tutulmalıdır. Anonym tweetler olmamalıdır.</li>
  
  <li>GET <a href="http://localhost:3000/tweet/findByUserId" target="_blank">http://localhost:3000/tweet/findByUserId</a> → Bir kullanıcının tüm tweetlerini getirmelidir.</li>
  
  <li>GET <a href="http://localhost:3000/tweet/findById" target="_blank">http://localhost:3000/tweet/findById</a> → Bir tweet için tüm bilgilerini getirmelidir.</li>
  
  <li>PUT <a href="http://localhost:3000/tweet/:id" target="_blank">http://localhost:3000/tweet/:id</a> → Tweet üzerinde değişiklik yapılmasını sağlar.</li>
  
  <li>DELETE <a href="http://localhost:3000/tweet/:id" target="_blank">http://localhost:3000/tweet/:id</a> → Tweet silinir. Sadece tweet sahibi silebilir.</li>
</ul>


<h4><b>MEDIUM</b></h4>
<ul>
  <li>POST <a href="http://localhost:3000/comment/" target="_blank">http://localhost:3000/comment/</a> → Bir tweete yorum yapılmasını sağlar.</li>
  
  <li>PUT <a href="http://localhost:3000/comment/:id" target="_blank">http://localhost:3000/comment/:id</a> → Yorum güncellenmesini sağlar.</li>
  
  <li>DELETE <a href="http://localhost:3000/comment/:id" target="_blank">http://localhost:3000/comment/:id</a> → Yorum silinir. Sadece tweet sahibi veya yorum sahibi silebilir.</li>
  
  <li>POST <a href="http://localhost:3000/like/" target="_blank">http://localhost:3000/like/</a> → Bir tweete like atılmasını sağlar.</li>
  
  <li>POST <a href="http://localhost:3000/dislike/" target="_blank">http://localhost:3000/dislike/</a> → Atılmış like geri alınır.</li>
</ul>


<h4><b>HARD</b></h4>
<ul>
  <li>POST <a href="http://localhost:3000/retweet/" target="_blank">http://localhost:3000/retweet/</a> → Bir tweetin retweet edilmesini sağlar.</li>
  
  <li>DELETE <a href="http://localhost:3000/retweet/:id" target="_blank">http://localhost:3000/retweet/:id</a> → Retweet silinmesini sağlar.</li>
</ul>

<h3><b>Mimari Zorunluluklar</b></h3>
<ul>
  <li>Veritabanı tasarımı proje öncesinde yapılmalıdır.</li>
  <li>Controller / Service / Repository / Entity katmanlı mimari kullanılmalıdır.</li>
  <li>Global Exception Handling tek merkezden yönetilmelidir.</li>
  <li>Entity alanlarında validasyon yapılmalıdır.</li>
  <li>Dependency Injection kurallarına uyulmalıdır.</li>
</ul>

<h3><b>Authentication & Security</b></h3>
<ul>
  <li>/register endpoint olmalıdır.</li>
  <li>/login endpoint olmalıdır.</li>
  <li>Security katmanı Spring Security ile yönetilmelidir.</li>
</ul>

<h3><b>Unit Test</b></h3>
<ul>
  <li>Yazılan fonksiyonların %30’u için unit test yazılmalıdır.</li>
</ul>

<h3><b>FullStack Developer Muscles</b></h3>
<ul>
  <li>Twitter API için basit bir React frontend oluşturulmalıdır.</li>
  <li>Frontend çok detaylı olmak zorunda değildir.</li>
  <li>Kullanıcının tweetlerini listeleyen bir component yapılabilir.</li>
</ul>

<h4><b>CORS Deneyi</b></h4>
<ul>
  <li>React uygulaması 3200 portundan ayağa kaldırılmalıdır.</li>
  <li>GET http://localhost:3000/tweet/findByUserId endpointine request atılmalıdır.</li>
  <li>Gelen tweetler ekrana basılmalıdır.</li>
  <li>CORS hatası gözlemlenmeli ve çözümü uygulanmalıdır.</li>
</ul>
<h3><b> Postman Collection</b></h3>

<p>
  You can test all API endpoints using the Postman collection below:
</p>

<ul>
  <li>
    <a href="./postman/twitter.postman_collection.json" target="_blank">
      Download Collection
    </a>
  </li>
</ul>
