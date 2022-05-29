<template>
  <div class="wrapper">
    <div style="visibility:hidden; margin:100px"></div>
    <div class="main main-raised">
      <div class="section profile-content">
        <profile-item
          :checkUserInfo="checkUserInfo"
          @deleteAccount="delMember"
        ></profile-item>
        <div class="container">
          <hr style="color:black" />
          <div class="md-layout-item  ">
            <tabs
              :tab-name="['My QnA', 'My Bookmark']"
              :tab-icon="['content_paste', 'star']"
              plain
              flex-column
              nav-pills-icons
              color-button="default"
              style="width: 100vw;"
            >
              <template slot="tab-pane-1">
                <b-table
                  sticky-header
                  :items="itemsById"
                  :fields="qna.fields"
                  :sort-by.sync="sortBy"
                  :sort-desc.sync="sortDesc"
                  responsive="sm"
                  style="max-height: 200px; width: 900px;"
                >
                  <template #cell(createdDate)="{item}">
                    {{ item.createdDate.substr(2, 9) }}
                  </template>
                  <template #cell(commentDto)="{item}">
                    {{ item.content }}
                  </template>
                </b-table>
                <md-button
                  class="md-success md-sm"
                  @click="moveQna"
                  style="float : right;"
                  >질문하기</md-button
                >
              </template>
              <template slot="tab-pane-2">
                <b-table
                  sticky-header
                  :items="bookMarks"
                  :fields="bookmark.fields"
                  :sort-by.sync="sortBy"
                  :sort-desc.sync="sortDesc"
                  :sort-compare="sort"
                  responsive="sm"
                  style="max-height: 200px;  width: 900px;"
                >
                  <template #cell(legal)="{item}">
                    {{ item.sidoname }} {{ item.gugunname }} {{ item.dong }}
                  </template>
                  <template #cell(price)="{item}">
                    {{ item.minPrice | money }} ~ {{ item.maxPrice | money }}
                  </template>
                </b-table>
                <md-button
                  class="md-success md-sm"
                  @click="moveMap"
                  style="float : right;"
                  >지도에서 보기</md-button
                >
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/components";
import ProfileItem from "@/views/components/account/ProfileItem.vue";
import { mapActions, mapGetters, mapMutations, mapState } from "vuex";

const accountStore = "accountStore";
const houseStore = "houseStore";
const qnaStore = "qnaStore";

export default {
  components: {
    Tabs,
    ProfileItem,
  },
  bodyClass: "profile-page",
  data() {
    return {
      sortBy: "아파트명",
      sortDesc: false,
      bookmark: {
        fields: [
          {
            key: "apartmentName",
            label: "아파트명",
            sortable: true,
            thStyle: { width: "35%" },
          },
          {
            key: "legal",
            label: "지역",
            sortable: true,
            thStyle: { width: "25%" },
          },
          {
            key: "buildyear",
            label: "건설년도",
            sortable: true,
            thStyle: { width: "10%" },
          },
          {
            key: "price",
            label: "거래가",
            sortable: true,
            thStyle: { width: "30%" },
          },
        ],
      },
      qna: {
        fields: [
          { key: "title", label: "제목", thStyle: { width: "20%" } },
          { key: "content", label: "내용", thStyle: { width: "40%" } },
          {
            key: "createdDate",
            label: "일자",
            sortable: true,
            thStyle: { width: "20%" },
          },
          { key: "commentDto", label: "답변", thStyle: { width: "20%" } },
        ],
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/city-profile.jpg"),
    },
    img: {
      type: String,
      default: require("@/assets/img/anonymous.png"),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
    ...mapGetters(accountStore, ["checkUserInfo"]),
    ...mapState(houseStore, ["bookMarks"]),
    ...mapState(qnaStore, ["itemsById"]),
  },
  methods: {
    ...mapActions(accountStore, ["deleteMember"]),
    ...mapActions(houseStore, ["getBookMarks"]),
    ...mapActions(qnaStore, ["getQnaListById"]),

    ...mapMutations(accountStore, ["SET_IS_LOGIN", "SET_USER_INFO", ,]),

    delMember() {
      if (confirm("탈퇴하시겠습니까?")) {
        try {
          this.deleteMember(this.checkUserInfo.id);
          alert("탈퇴하였습니다.");
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          this.$router.push("/");
        } catch {
          alert("탈퇴 중 문제가 발생했습니다.");
        }
      }
    },
    moveQna() {
      this.$router.push("/board/question");
    },
    moveMap() {
      this.$router.push("/aptView/interests");
    },

    sort(aRow, bRow, key, sortDesc, formatter, compareOptions, compareLocale) {
      if (key == "price") {
        const a = parseInt(aRow["minPrice"].replace(/,/g, ""));
        const b = parseInt(bRow["minPrice"].replace(/,/g, ""));

        return a < b ? -1 : a > b ? 1 : 0;
      } else {
        return toString(aRow).localeCompare(
          this.toString(bRow),
          compareLocale,
          compareOptions
        );
      }
    },
    toString(value) {
      if (value === null || typeof value === "undefined") {
        return "";
      } else if (value instanceof Object) {
        return Object.keys(value)
          .sort()
          .map((key) => toString(value[key]))
          .join(" ");
      } else {
        return String(value);
      }
    },
  },
  async created() {
    await this.getBookMarks(this.checkUserInfo.id);
    await this.getQnaListById(this.checkUserInfo.id);
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
</style>
