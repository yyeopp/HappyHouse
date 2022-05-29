import { apiInstance } from "./index.js";

const api = apiInstance();

function listQuestion(success, fail) {
  api
    .get(`/qna/list`)
    .then(success)
    .catch(fail);
}
function listQuestionById(id, success, fail) {
  api
    .get(`/qna/list/${id}`)
    .then(success)
    .catch(fail);
}

function writeAnswer(answer, success, fail) {
  api
    .post(`/qna/write/comment`, JSON.stringify(answer))
    .then(success)
    .catch(fail);
}

function writeQuestion(question, success, fail) {
  api
    .post(`/qna/write`, JSON.stringify(question))
    .then(success)
    .catch(fail);
}
export { listQuestion, listQuestionById, writeAnswer, writeQuestion };
