export function authHeader() {
  // return authorization header with basic auth credentials
  const user = JSON.parse(localStorage.getItem('user'));
  if (user != null) {
    if (isExpired(getExpirationDate(user.accessToken))) {
      localStorage.removeItem("user");
    }
    
  }
  

  if (user && user.accessToken) {
    const userRole = user.roles.includes("ROLE_ADMIN")? "ADMIN" : ""
    return { Authorization: 'Bearer ' + userRole+ ""+ user.accessToken };
  } else {
    return {};
  }
}


const isExpired = (exp) => {
  if (!exp) {
      return false;
  }

  return Date.now() > exp;
};

const getExpirationDate = (jwtToken) => {
  if (!jwtToken) {
      return null;
  }

  const jwt = JSON.parse(atob(jwtToken.split('.')[1]));

  // multiply by 1000 to convert seconds into milliseconds
  return ((jwt && jwt.exp) && jwt.exp * 1000) || null;
};