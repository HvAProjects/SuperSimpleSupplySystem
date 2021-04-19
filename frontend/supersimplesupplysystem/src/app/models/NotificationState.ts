export enum NotificationState {
  unseen,
  seen,
  declined,
  accepted
}

// tslint:disable-next-line:no-namespace
export namespace NotificationState {

  const values = {
    [NotificationState.unseen]: '',
    [NotificationState.seen]: '',
    [NotificationState.declined]: 'You have declined this request',
    [NotificationState.accepted]: 'You have accepted this request',
  };

  export function getStringValue(state: NotificationState | string): string {
    if (typeof state === typeof NotificationState) {
      return values[state];
    }

    return values[Object.keys(values).find(k => NotificationState[k] === state)];
  }

  export function getName(state: NotificationState): string {
    return NotificationState[state];
  }
}
