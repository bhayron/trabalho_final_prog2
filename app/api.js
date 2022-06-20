import axios from "axios";

export const api = axios.create({
    baseURL:'http://10.10.149.94:6868',
});

