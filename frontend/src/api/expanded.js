import { apiInstance } from "./index.js";

const api = apiInstance();

function hospitaListRange(params, success, fail) {
  api
    .get(`/expanded/hospitallist/`, { params: params })
    .then(success)
    .catch(fail);
}
function schoolListRange(params, success, fail) {
  api
    .get(`/expanded/schoollist/`, { params: params })
    .then(success)
    .catch(fail);
}
function busStationListRange(params, success, fail) {
  api
    .get(`/expanded/busstationlist/`, { params: params })
    .then(success)
    .catch(fail);
}
function subwayListRange(params, success, fail) {
  api
    .get(`/expanded/subwaylist/`, { params: params })
    .then(success)
    .catch(fail);
}

function nearObject({ lng, lat }, success, fail) {
  api
    .get(`/expanded/near/${lng}/${lat}`)
    .then(success)
    .catch(fail);
}

export {
  hospitaListRange,
  schoolListRange,
  busStationListRange,
  subwayListRange,
  nearObject,
};
