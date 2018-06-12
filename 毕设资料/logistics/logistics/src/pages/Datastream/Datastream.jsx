import React, { Component } from 'react';
import FilterTable from './components/FilterTable';

export default class OrderItem extends Component {
  static displayName = 'OrderItem';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="orderitem-page">
        <FilterTable />
      </div>
    );
  }
}
