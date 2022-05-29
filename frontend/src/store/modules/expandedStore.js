import {
  hospitaListRange,
  schoolListRange,
  busStationListRange,
  subwayListRange,
  nearObject,
} from "@/api/expanded.js";

const expandedStore = {
  namespaced: true,

  state: {
    hospitals: [],
    schools: [],
    busStations: [],
    subways: [],
    nearSchools: [],
    nearSubways: [],
    nearHospital: [],
  },
  mutations: {
    SET_HOSPITAL_LIST: (state, hospitals) => {
      state.hospitals = hospitals;
    },
    SET_SCHOOL_LIST: (state, schools) => {
      state.schools = schools;
    },
    SET_BUSSTATION_LIST: (state, busStations) => {
      state.busStations = busStations;
    },
    SET_SUBWAY_LIST: (state, subways) => {
      state.subways = subways;
    },
    SET_NEAR_SCHOOL_LIST: (state, school) => {
      state.nearSchools = school;
    },
    SET_NEAR_HOSPITAL_LIST: (state, hospital) => {
      state.nearHospital = hospital;
    },
    SET_NEAR_SUBWAY_LIST: (state, subways) => {
      state.nearSubways = subways;
    },
  },

  actions: {
    //======================== expanded
    getHospitaListRange: ({ commit }, params) => {
      hospitaListRange(
        params,
        (response) => {
          commit("SET_HOSPITAL_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSchoolListRange: ({ commit }, params) => {
      schoolListRange(
        params,
        (response) => {
          commit("SET_SCHOOL_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getBusStationListRange: ({ commit }, params) => {
      busStationListRange(
        params,
        (response) => {
          commit("SET_BUSSTATION_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSubwayListRange: ({ commit }, params) => {
      subwayListRange(
        params,
        (response) => {
          commit("SET_SUBWAY_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getNearObject: ({ commit }, { lng, lat }) => {
      nearObject(
        { lng, lat },
        (response) => {
          commit("SET_NEAR_SCHOOL_LIST", response.data.school);
          commit("SET_NEAR_HOSPITAL_LIST", response.data.hospital);
          commit("SET_NEAR_SUBWAY_LIST", response.data.subway);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default expandedStore;
