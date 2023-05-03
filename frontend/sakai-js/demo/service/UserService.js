import axios from "axios";
import { ServiceBase } from './ServiceBase';

export class UserService extends ServiceBase {

  constructor(){
    super("users");
  }
}
