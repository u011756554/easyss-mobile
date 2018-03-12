<template>
  <div class="wrapper">
    <text class="greeting">The Easyss is ready!</text>
    <text class="message">{{greeting}}</text>
    <div>
      <text class="button" @click="start">Start</text>
    </div>
  </div>
  
</template>

<script>
  module.exports = {
    data: function() {
      return {
        greeting: 'hello world!'
      }
    },
    methods: {
      start: function() {
        var easyss = weex.requireModule('easyss');
        var ret = easyss.printLog("hello easyss...");

        this.greeting = ret;
      }
    }
  }
</script>

<style scoped>
  .wrapper {
    justify-content: center;
    align-items: center;
  }
  .logo {
    width: 424px;
    height: 200px;
  }
  .greeting {
    margin-top: 10px;
    font-size: 50px;
    color: #41B883;
  }
  .message {
    margin: 30px;
    font-size: 32px;
    color: #727272;
  }
  .button {
    font-size: 50px;
    width: 400px;
    text-align: center;
    margin-top: 30px;
    padding-top: 20px;
    padding-bottom: 20px;
    border-width: 2px;
    border-style: solid;
    color: #666666;
    border-color: #DDDDDD;
    background-color: #F5F5F5
  }
</style>
