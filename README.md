# ults
Otp genration and authentication


Secure OTP Generation and Authentication


database : mysql


created  APIs

1-> POST - /api/user/    for creating user 
    @Reuestbody  {
                      "name":"junaid",
                      "password":"12345",
                      "email":"junu@gmail.com",
                      "role":{
                          "role":"user"
                      }
                  }


2-> GET - /api/user/sendOtp   for Login user need otp verification so this api will generate otp with request body username(as email) and password
     @Reuestbody {
                     "username":"junu@gmail.com",
                     "password":"123425"

                 }


3-> GET - /api/user/verify/{userid}  for verifiying generated OTP with same credential , otp will expire after 5 minutes
    @PathVariable  user id
    @RequestMapping {
                        "otp":"367456"
                    }

4-> GET -/api/user/resend/{userid}  we can regenerate OTP if previous one is expired
    @PathVariable userId

5-> GET - /api/user/authenticate for authenticate the user is verified or not with requet body username(as emial) and password
    @RequestBody {
                     "username":"junu@gmail.com",
                     "password":"12345"

                 }


6-> GET - /api/user/autherization for autherize the user by given role in request body (Request body contain username(as Email), password and role)
    @RequestBody {
                     "username":"junu@gmail.com",
                     "password":"12345",
                     "role":"user"

                 }
