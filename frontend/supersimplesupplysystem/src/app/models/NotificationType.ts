export enum NotificationType {
  householdInvitation
}

// tslint:disable-next-line:no-namespace
export namespace NotificationType {

  const values = {
    [NotificationType.householdInvitation]: 'Household Invitation',
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
