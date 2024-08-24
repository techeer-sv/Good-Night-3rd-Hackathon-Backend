import { IsEnum } from 'class-validator';
import { WishStatus } from './create-wish.dto';

export class UpdateWishStatusDto {
  @IsEnum(WishStatus)
  status: WishStatus;
}
