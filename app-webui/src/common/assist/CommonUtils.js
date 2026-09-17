function isUndefined(val) {
  return typeof val === "undefined";
}

function isDefined(val) {
  return typeof val !== "undefined";
}

function isEmpty(val) {
  return (
    typeof val === "undefined" || val == null || val === "" || val.length == 0
  );
}

function isNotExist(val) {
  return typeof val === "undefined" || val == null;
}

function range(min, max) {
  const array = [];
  let j = 0;
  for (let i = min; i <= max; i++) {
    array[j] = i;
    j++;
  }
  return array;
}

function fromDate(dateObj) {
  if (dateObj != null && typeof dateObj.getMonth === "function") {
    const dateString =
      dateObj.getFullYear() +
      "-" +
      (dateObj.getMonth() + 1) +
      "-" +
      dateObj.getDate();
    return dateString;
  }
  return null;
}

function toDate(dateString) {
  if (dateString != null && dateString !== "") {
    const parts = dateString.split("-");
    const date = new Date();
    date.setFullYear(parseInt(parts[0]));
    date.setMonth(parseInt(parts[1]) - 1, parseInt(parts[2]));
    date.setUTCHours(0, 0, 0, 0);
    return date;
  }
  return null;
}

// 2014-12-20T22:10:00 -> Date
function toDateTime(dateTimeString) {
  var parts = dateTimeString.match(/\d+/g);
  return new Date(
    parts[0],
    parts[1] - 1,
    parts[2],
    parts[3],
    parts[4],
    parts[5]
  );
}

function getProperty(obj, path) {
  const stack = path.split(".");
  let cobj = obj;
  while (stack.length > 1) {
    cobj = cobj[stack.shift()];
    if (isNotExist(cobj)) {
      return cobj;
    }
  }
  return cobj[stack.shift()];
}

function setProperty(obj, path, val) {
  const stack = path.split(".");
  let cobj = obj;
  while (stack.length > 1) {
    cobj = cobj[stack.shift()];
    if (isNotExist(cobj)) {
      throw new Error("Unreachable path: " + path);
    }
  }
  cobj[stack.shift()] = val;
}

function replaceContainer(Component, options) {
  const frag = document.createDocumentFragment();
  const component = new Component(Object.assign({}, options, { target: frag }));
  options.target.replaceWith(frag);
  return component;
}

export {
  isUndefined,
  isDefined,
  isEmpty,
  isNotExist,
  range,
  fromDate,
  toDate,
  toDateTime,
  getProperty,
  setProperty,
  replaceContainer,
};
