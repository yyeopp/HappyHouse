<template>
  <div class="question-board-item">
    <p class="question-content">{{ item.content }}</p>
    <hr class="my-2" />
    <b-card v-if="item.commentDto">
      <div class="answer">
        <i class="material-icons">
          subdirectory_arrow_rightdiv
        </i>
        <p class="comment-content">
          {{ item.commentDto.content }}
        </p>
      </div>
    </b-card>
    <b-form v-if="!item.commentDto && isAdmin">
      <b-form-group id="input-group-1" label-for="input-1">
        <b-form-textarea
          id="input-1"
          v-model="content"
          placeholder="답변을 입력하세요"
          rows="5"
          required
          style="margin-bottom: 10px"
        ></b-form-textarea>
      </b-form-group>
      <button
        @click.prevent="onSubmit(item.boardId)"
        class="md-button md-theme-default"
        style="margin-bottom: 20px;"
      >
        <div class="md-ripple">
          <div class="md-button-content">작성</div>
        </div>
      </button>
    </b-form>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

const accountStore = "accountStore";
export default {
  props: {
    item: Object,
  },
  data() {
    return {
      content: "",
    };
  },
  computed: {
    ...mapGetters(accountStore, ["checkUserInfo"]),

    isAdmin() {
      if (this.checkUserInfo == null) {
        return false;
      }
      return this.checkUserInfo.id == "admin";
    },
  },
  methods: {
    onSubmit(boardId) {
      this.$emit("writeAnswer", boardId, this.content);
    },
  },
};
</script>

<style lang="scss" scoped>
i {
  display: inline;
  position: absolute;
  top: 10px;
}

table.text-center * {
  text-align: center;
}

.question-board-item {
  width: 85%;
  float: right;
}

.comment-content {
  margin: 0 0 0 24px;
}

.question-content {
  margin-bottom: 0;
}
</style>
