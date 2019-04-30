// pages/list/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    wx.request({
      url: "http://127.0.0.1:8080/wechat-app-demo/superadmin/listarea",
      data: {},
      method: 'GET',
      success: function(res) {
        var list = res.data.areaList;
        if (list == null) {
          var toastText = '获取数据失败' + res.data.errMsg;
          wx.showToast({
            title: toastText,
            icon: '',
            duration: 2000
          });
        } else {
          that.setData({
            list: list
          });
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  /**
   * 自定义添加区域信息函数
   */
  addArea: function() {
    wx.navigateTo({
      url: '../operation/operation',
    })
  },

  /**
   * 自定义删除区域信息函数
   */
  deleteArea: function(e) {
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确定要删除[' + e.target.dataset.areaname + ']吗？',
      success: function(sm) {
        if (sm.confirm) {
          // 用户点击了确定 可以调用删除方法了
          wx.request({
            url: "http://127.0.0.1:8080/wechat-app-demo/superadmin/removearea",
            data: {
              "areaId": e.target.dataset.areaid
            },
            method: 'GET',
            success: function(res) {
              var result = res.data.success
              var toastText = "删除成功！";
              if (result != true) {
                toastText = "删除失败" + res.data.errMsg;
              } else {
                that.data.list.splice(e.target.dataset.index, 1)
                //渲染数据
                that.setData({
                  list: that.data.list
                });
              }
              wx.showToast({
                title: toastText,
                icon: '',
                duration: 2000
              });
            }
          })
        }
      }
    })
  }

})