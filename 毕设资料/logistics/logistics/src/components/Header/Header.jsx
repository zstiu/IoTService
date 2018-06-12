import React, { Component } from 'react';
import { Input, Balloon, Icon } from '@icedesign/base';
import Menu from '@icedesign/menu';
import Logo from '../Logo';
import './Header.scss';

const MENUS = [
  {
    name: '公司简介',
    path: 'about',
  },
  {
    name: '业务概述',
    path: 'bussiness',
  },
  {
    name: '箱体介绍',
    path: 'introduce',
  },
  {
    name: '联系我们',
    path: 'contact',
  }
];

export default class Header extends Component {
  renderBalloonContent = (menu, idx) => {
    return (
      <Menu.Item key={idx}>
        <Balloon
          className="header-balloon-content"
          closable={false}
          triggerType="click"
          trigger={
            <a>
              {menu.name}{' '}
              <Icon
                size="xxs"
                type="arrow-down-filling"
                className="arrow-down-filling-icon"
              />
            </a>
          }
        >
          {menu.children.map((subMenu, idx) => {
            return (
              <a href="#" className="custom-sub-menu" key={idx}>
                {subMenu.name}
              </a>
            );
          })}
        </Balloon>
      </Menu.Item>
    );
  };
  scrollToAnchor = (anchorName) => {
    if (anchorName) {
        // 找到锚点
        let anchorElement = document.getElementById(anchorName);
        // 如果对应id的锚点存在，就跳转到锚点
        if(anchorElement) { anchorElement.scrollIntoView({block: 'start', behavior: 'smooth'}); }
    }
  }
  renderMenuItem = () => {
    return MENUS.map((menu, idx) => {
      if (menu.children) {
        return this.renderBalloonContent(menu, idx);
      }
      return (
        <Menu.Item key={menu.path}>
          <a  onClick={()=>this.scrollToAnchor(menu.path)} className="fontSize">{menu.name}</a>
        </Menu.Item>
      );
    });
  };

  render() {
    return (
      <div className="header-container">
        <div className="header-content">
          <Logo isDark={true} />
          <div className="header-navbar">
            <Menu className="header-navbar-menu" mode="horizontal">
              {this.renderMenuItem()}
            </Menu>
          </div>
        </div>
      </div>
    );
  }
}
