/**
 * 
 */
 "use strict";
 let buggerBtn = document.querySelector("#buggerBtn");
let menu = document.querySelector("header ul");
let menu_wrap = document.querySelector(".menu_wrap");
let buggerBtn_hide = document.querySelector("#buggerBtn_hide");
let cover_close = document.querySelector(".cover_close");

buggerBtn.addEventListener("click", () => {
  console.log("buggerBtn Clicked");
  menu_wrap.style.zIndex = "999";
  menu_wrap.style.opacity = "1";
  cover_close.style.zIndex = "998";
  cover_close.style.opacity = "0.4";
});

buggerBtn_hide.addEventListener("click", () => {
  console.log("buggerBtn Clicked");
  menu_wrap.style.zIndex = "0";
  menu_wrap.style.opacity = "0";
  cover_close.style.zIndex = "0";
  cover_close.style.opacity = "0";
});

cover_close.addEventListener("click", () => {
  menu_wrap.style.zIndex = "0";
  menu_wrap.style.opacity = "0";
  cover_close.style.zIndex = "0";
  cover_close.style.opacity = "0";
});
 