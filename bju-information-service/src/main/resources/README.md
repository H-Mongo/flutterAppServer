###JWT基本信息
* payload(载荷)：载荷就是存放有效信息的地方。
    有效信息包含三个部分
    * 1.标准中注册的声明
    * 2.公共的声明
    * 3.私有的声明
* 标准中注册的声明 (建议但不强制使用) ：
  * iss: jwt签发者
  * sub: 面向的用户(jwt所面向的用户)
  * aud: 接收jwt的一方
  * exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
  * nbf: 定义在什么时间之前，该jwt都是不可用的.
  * iat: jwt的签发时间
  * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
* 公共的声明 ：
    公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.
* 私有的声明 ：
 私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。