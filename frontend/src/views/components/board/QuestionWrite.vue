<template>
  <div class="container">
    <div class="mx-auto">
      <div class="title">
        <h1>QnA 작성</h1>
      </div>
    </div>
    <div class="md-layout">
      <div class="md-layout-item md-size-75 mx-auto">
        <b-form>
          <b-form-group id="input-group-1" label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.title"
              type="text"
              placeholder="제목을 입력하세요."
              required
              style="margin-bottom: 10px"
            ></b-form-input>
            <b-form-textarea
              id="input-1"
              v-model="form.content"
              placeholder="내용을 입력하세요"
              rows="8"
              required
              style="margin-bottom: 10px"
            ></b-form-textarea>
          </b-form-group>
          <button
            @click.prevent="onSubmit"
            class="md-button md-info md-theme-default"
            style="margin-bottom: 20px; float:right"
          >
            <div class="md-ripple">
              <div class="md-button-content">Submit</div>
            </div>
          </button>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const accountStore = "accountStore";
const qnaStore = "qnaStore";

export default {
  bodyClass: "profile-page",
  data() {
    return {
      form: {
        title: "",
        content: "",
      },
    };
  },
  computed: {
    ...mapGetters(accountStore, ["checkUserInfo"]),
  },
  methods: {
    ...mapActions(qnaStore, ["writeQ"]),

    onSubmit() {
      if (!this.form.title || !this.form.content) {
        return;
      }
      try {
        this.writeQ({
          memberId: this.checkUserInfo.id,
          title: this.form.title,
          content: this.form.content,
          boardListId: 1,
        });
        alert("글 작성에 성공했습니다.");
        this.$router.push("/board/question");
      } catch {
        alert("글 작성 중 오류가 발생했습니다.");
        this.$router.push("/board/question");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}

a:hover {
  cursor: pointer;
}

i {
  display: inline;
  position: absolute;
  top: 10px;
}

p {
  margin: 0 0 0 24px;
}

table.text-center * {
  text-align: center;
}
</style>
