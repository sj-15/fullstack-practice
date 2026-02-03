import axios from "axios";
import { environment } from "../env/environment";
import { AppConstant } from "../constant/AppConstant";

class LoginService {
  login(user) {
    return axios.get(
      `${environment.rooturl}${AppConstant.LOGIN_API_URL}`,
      user
    );
  }
}

export default new LoginService();
