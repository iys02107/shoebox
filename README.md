# SHOEBOX

## 20220531 발견된 이슈


로그인 시 오류 발생

오류 코드 : {"timestamp":"2022-05-31T14:30:11.913+00:00","status":999,"error":"None","message":"No message available"}

오류 코드 화면에서 뒤로가기를 하면 로그인이 되어있다.
******

확인 결과 : favicon이 없어서 오류 발생
해결: 시큐리티 config 에서 웹 이그노잉에 ```.antMatchers("/favicon.ico", "/resources/**", "/error")``` 를 추가
