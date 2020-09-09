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
        name: 'Store Creation',
        to: '/store/creation',
        icon: 'cil-institution'
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