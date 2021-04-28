export enum NotificationType {
  householdInvitation,
  productAboutToExpire,
  productExpired
}

// tslint:disable-next-line:no-namespace
export namespace NotificationType {

  const values = {
    [NotificationType.householdInvitation]: 'Household invitation',
    [NotificationType.productAboutToExpire]: 'Product about to expire',
    [NotificationType.productExpired]: 'Product expired',
  };

  export function getStringValue(value: NotificationType | string): string {
    if (typeof value === typeof NotificationType) {
      return values[value];
    }

    return values[Object.keys(values).find(k => NotificationType[k] === value)];
  }

  export function getName(value: NotificationType): string {
    return NotificationType[value];
  }
}
