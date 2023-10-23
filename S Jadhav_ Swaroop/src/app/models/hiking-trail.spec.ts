import { HikingTrail } from './hiking-trail';

describe('HikingTrail', () => {
  it('should create an instance', () => {
    expect(new HikingTrail('', 0, 0, 0, '', '')).toBeTruthy();
  });
});
