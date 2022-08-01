import { post,get } from "./service";
export const loginapi= data=>{
    return post({
        url:"/user",
        data
    })
}