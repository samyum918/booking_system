export default [
  {
    _name: 'CSidebarNav',
    _children: [
      {
        _name: 'CSidebarNavTitle',
        _children: ['Store Setup']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Store Overview',
        to: '/store/overview',
        icon: 'cil-institution'
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Store Timeslot Overview',
        to: '/store-timeslot/overview',
        icon: 'cil-calendar'
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Block Booking']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Block a date',
        to: '/block-booking/date',
        icon: 'cil-calendar'
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Block a timeslot',
        to: '/block-booking/timeslot',
        icon: 'cil-clock'
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Records']
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Booking',
        to: '/records/booking',
        icon: 'cil-calendar-check'
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Cancel Booking',
        to: '/records/cancel-booking',
        icon: 'cil-x-circle'
      },
      {
        _name: 'CSidebarNavTitle',
        _children: ['Components']
      },
      {
        _name: 'CSidebarNavDropdown',
        name: 'Base',
        route: '/base',
        icon: 'cil-puzzle',
        items: [
          {
            name: 'Breadcrumbs',
            to: '/base/breadcrumbs'
          },
          {
            name: 'Forms',
            to: '/base/forms'
          },
          {
            name: 'Tables',
            to: '/base/tables'
          }
        ]
      }
    ]
  }
]