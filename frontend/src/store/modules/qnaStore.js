import {
  listQuestion,
  writeAnswer,
  writeQuestion,
  listQuestionById,
} from "@/api/qna.js";

const qnaStore = {
  namespaced: true,
  state: {
    items: [],
    itemsById: [],
  },
  mutations: {
    SET_QUESTION_LIST: (state, qnaList) => {
      state.items = qnaList;
    },
    SET_QUESTION_LIST_BY_ID: (state, qnaListById) => {
      if (qnaListById != null) {
        state.itemsById = qnaListById;
      }
    },
    WRITE_QUESTION: () => {},
    WRITE_ANSWER: () => {},
  },
  actions: {
    getQnaList: ({ commit }) => {
      listQuestion(
        ({ data }) => {
          commit("SET_QUESTION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getQnaListById: ({ commit }, id) => {
      listQuestionById(
        id,
        ({ data }) => {
          commit("SET_QUESTION_LIST_BY_ID", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    writeQ: ({ commit }, question) => {
      writeQuestion(
        question,
        () => {
          commit("WRITE_QUESTION");
        },
        () => {}
      );
    },
    writeAns: ({ commit }, answer) => {
      writeAnswer(
        answer,
        () => {
          commit("WRITE_ANSWER");
        },
        () => {}
      );
    },
  },
};
export default qnaStore;
