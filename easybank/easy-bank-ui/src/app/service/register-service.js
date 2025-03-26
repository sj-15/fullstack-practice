import axios from "axios";
import { environment } from "../env/environment";
import { AppConstant } from "../constant/AppConstant";

class RegisterService {
  register(user) {
    return axios.post(
      `${environment.rooturl}${AppConstant.REGISTER_API_URL}`,
      user
    );
  }
}

export default new RegisterService();
