<template>
  <div class="container">
    <div class="mx-auto">
      <div class="title">
        <h1>공지 작성</h1>
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
import { mapActions } from "vuex";

const noticeStore = "noticeStore";

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
  methods: {
    ...mapActions(noticeStore, ["writeNotice"]),
    onSubmit() {
      if (!this.form.title || !this.form.content) {
        return;
      }
      try {
        this.writeNotice({
          memberId: "admin",
          title: this.form.title,
          content: this.form.content,
          hit: 1,
          boardListId: 3,
        });
        alert("등록 성공!");
        this.$router.push("/board/notice");
      } catch {
        alert("공지 등록 중 에러 발생");
        this.$router.push("/board/notice");
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
