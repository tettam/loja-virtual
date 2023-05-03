import axios from "axios";
import { ServiceBase } from './ServiceBase';

export class PermissionService extends ServiceBase {
  
  constructor(){
    super('permissions')
  }
}
