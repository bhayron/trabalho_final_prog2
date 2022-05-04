import http from "./http-common";

const getAll = () => {
  return http.get("/alunos");
};

const get = id => {
  return http.get(`/alunos/${id}`);
};

const create = data => {
  console.log("http",data);
  return http.post("/alunos", data);
};

const update = (id, data) => {
  return http.put(`/alunos/${id}`, data);
};

const remove = id => {
  return http.delete(`/alunos/${id}`);
};

const removeAll = () => {
  return http.delete(`/alunos`);
};

const findByTitle = title => {
  return http.get(`/alunos?title=${title}`);
};

const AlunoService = {
  getAll,
  get,
  create,
  update,
  remove,
  removeAll,
  findByTitle
};

export default AlunoService;